//package com.leetcode.editor.util;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.net.ftp.FTPClient;
//import org.apache.commons.net.ftp.FTPReply;
//
//import java.io.*;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//public class FtpUtil {
//    /**
//     * FTP地址
//     **/
//    private static String ftpAddress;
//
//    /**
//     * FTP端口
//     **/
//    private static int ftpPort = 0;
//
//    /**
//     * FTP用户名
//     **/
//    private static String ftpUsername;
//
//    /**
//     * FTP密码
//     **/
//    private static String ftpPassword;
//
//    /**
//     * FTP基础目录
//     **/
//    private String basePath = "/";
//
//    /**
//     * 初始化登录ftp 默认false 登录成功返回true
//     **/
//    private Boolean b = false;
//
//    public Boolean getB() {
//        return b;
//    }
//
//    /**
//     * 初始化登录ftp，连接失败 返回b 为：false ,成功 为 ：true
//     *
//     */
//    public FtpUtil() {
//        b = login(ftpAddress, ftpPort, ftpUsername, ftpPassword);
//    }
//
//    /**
//     * 本地字符编码
//     **/
//    private static String localCharset = "GBK";
//
//    /**
//     * FTP协议里面，规定文件名编码为iso-8859-1
//     **/
//    private static String serverCharset = "ISO-8859-1";
//
//    /**
//     * UTF-8字符编码
//     **/
//    private static final String CHARSET_UTF8 = "UTF-8";
//
//    /**
//     * OPTS UTF8字符串常量
//     **/
//    private static final String OPTS_UTF8 = "OPTS UTF8";
//
//    /**
//     * 设置缓冲区大小
//     **/
//    private static final int BUFFER_SIZE = 1024 * 1024 * 10;
//
//    /**
//     * FTPClient对象
//     **/
//    private static FTPClient ftpClient = null;
//
//    /**
//     * 下载指定文件到本地
//     *
//     * @param ftpPath  FTP服务器文件相对路径，例如：test/123
//     * @param fileName 要下载的文件名，例如：test.txt
//     * @param savePath 保存文件到本地的路径，例如：D:/test
//     * @return 成功返回true，否则返回false
//     */
//    public boolean downloadFile(String ftpPath, String fileName, String savePath) {
//        // 登录
//        boolean flag = false;
//        if (ftpClient != null) {
//            try {
//                String path = changeEncoding(basePath + ftpPath);
//                // 判断是否存在该目录
//                if (!ftpClient.changeWorkingDirectory(path)) {
//                    log.error(basePath + ftpPath + "该目录不存在");
//                    return flag;
//                }
//                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
//                String[] fs = ftpClient.listNames();
//                // 判断该目录下是否有文件
//                if (fs == null || fs.length == 0) {
//                    log.error(basePath + ftpPath + "该目录下没有文件");
//                    return flag;
//                }
//                for (String ff : fs) {
//                    String ftpName = new String(ff.getBytes(CHARSET_UTF8), CHARSET_UTF8);
//                    if (ftpName.equals(fileName)) {
//                        File file = new File(savePath + '/' + ftpName);
//                        try {
//                            OutputStream os = new FileOutputStream(file);
//                            flag = ftpClient.retrieveFile(ff, os);
//                        } catch (Exception e) {
//                            log.error(e.getMessage(), e);
//                        }
//                        break;
//                    }
//                }
//            } catch (IOException e) {
//                log.error("下载文件失败", e);
//            } finally {
//                Boolean close = closeConnect();
//                log.info("连接是否关闭：" + close);
//            }
//        }
//        return flag;
//    }
//
//    /**
//     * 下载该目录下所有文件到本地
//     *
//     * @param ftpPath  FTP服务器上的相对路径，例如：test/123
//     * @param savePath 保存文件到本地的路径，例如：D:/test
//     * @return 成功返回true，否则返回false
//     */
//    public boolean downloadFiles(String ftpPath, String savePath) {
//        // 登录
//        boolean flag = false;
//        if (ftpClient != null) {
//            try {
//                String path = changeEncoding(basePath + ftpPath);
//                // 判断是否存在该目录
//                if (!ftpClient.changeWorkingDirectory(path)) {
//                    log.error(basePath + ftpPath + "该目录不存在");
//                    return flag;
//                }
//                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
//                String[] fs = ftpClient.listNames();
//                // 判断该目录下是否有文件
//                if (fs == null || fs.length == 0) {
//                    log.error(basePath + ftpPath + "该目录下没有文件");
//                    return flag;
//                }
//                for (String ff : fs) {
//                    String ftpName = new String(ff.getBytes(CHARSET_UTF8), CHARSET_UTF8);
//                    File file = new File(savePath + '/' + ftpName);
//                    try {
//                        OutputStream os = new FileOutputStream(file);
//                        ftpClient.retrieveFile(ff, os);
//                    } catch (Exception e) {
//                        log.error(e.getMessage(), e);
//                    }
//                }
//                flag = true;
//            } catch (IOException e) {
//                log.error("下载文件失败", e);
//            } finally {
//                Boolean close = closeConnect();
//                log.info("连接是否关闭：" + close);
//            }
//        }
//        return flag;
//    }
//
//    /**
//     * 获取该目录下所有文件,以字节数组返回
//     *
//     * @param ftpPath FTP服务器上文件所在相对路径，例如：test/123
//     * @return Map<String, Object> 其中key为文件名，value为字节数组对象
//     */
//    public Map<String, byte[]> getFileBytes(String ftpPath) {
//        // 登录
//        Map<String, byte[]> map = new HashMap<String, byte[]>();
//        if (ftpClient != null) {
//            try {
//                String path = changeEncoding(basePath + ftpPath);
//                // 判断是否存在该目录
//                if (!ftpClient.changeWorkingDirectory(path)) {
//                    log.error(basePath + ftpPath + "该目录不存在");
//                    return map;
//                }
//                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
//                String[] fs = ftpClient.listNames();
//                // 判断该目录下是否有文件
//                if (fs == null || fs.length == 0) {
//                    log.error(basePath + ftpPath + "该目录下没有文件");
//                    return map;
//                }
//                for (String ff : fs) {
//                    try {
//                        InputStream is = ftpClient.retrieveFileStream(ff);
//                        String ftpName = new String(ff.getBytes(CHARSET_UTF8), CHARSET_UTF8);
//                        if (!isDatFile(ftpName)) {
//                            continue;
//                        }
//                        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//                        byte[] buffer = new byte[BUFFER_SIZE];
//                        int readLength;
//                        while ((readLength = is.read(buffer, 0, BUFFER_SIZE)) > 0) {
//                            byteStream.write(buffer, 0, readLength);
//                        }
//                        map.put(ftpName, byteStream.toByteArray());
//                        ftpClient.completePendingCommand(); // 处理多个文件
//                    } catch (Exception e) {
//                        log.error(e.getMessage(), e);
//                    }
//                }
//            } catch (IOException e) {
//                log.error("获取文件失败", e);
//            } finally {
//                Boolean close = closeConnect();
//                log.info("连接是否关闭：" + close);
//            }
//        }
//        return map;
//    }
//
//    /**
//     * 根据名称获取文件，以字节数组返回
//     *
//     * @param ftpPath  FTP服务器文件相对路径，例如：test/123
//     * @param fileName 文件名，例如：test.xls
//     * @return byte[] 字节数组对象
//     */
//    public byte[] getFileBytesByName(String ftpPath, String fileName) {
//        // 登录
//        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
//        if (ftpClient != null) {
//            try {
//                String path = changeEncoding(basePath + ftpPath);
//                // 判断是否存在该目录
//                if (!ftpClient.changeWorkingDirectory(path)) {
//                    log.error(basePath + ftpPath + "该目录不存在");
//                    return byteStream.toByteArray();
//                }
//                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
//                String[] fs = ftpClient.listNames();
//                // 判断该目录下是否有文件
//                if (fs == null || fs.length == 0) {
//                    log.error(basePath + ftpPath + "该目录下没有文件");
//                    return byteStream.toByteArray();
//                }
//                for (String ff : fs) {
//                    String ftpName = new String(ff.getBytes(CHARSET_UTF8), CHARSET_UTF8);
//                    if (isDatFile(ftpName)) {
//                        int index = ftpName.indexOf(fileName);
//                        if (index != -1) {
//                            try {
//                                InputStream is = ftpClient.retrieveFileStream(ff);
//                                byte[] buffer = new byte[BUFFER_SIZE];
//                                int len;
//                                while ((len = is.read(buffer, 0, BUFFER_SIZE)) != -1) {
//                                    byteStream.write(buffer, 0, len);
//                                }
//                            } catch (Exception e) {
//                                log.error(e.getMessage(), e);
//                            }
//                            break;
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                log.error("获取文件失败", e);
//            } finally {
//                Boolean close = closeConnect();
//                log.info("连接是否关闭：" + close);
//            }
//        }
//        return byteStream.toByteArray();
//    }
//
//    /**
//     * 获取该目录下所有文件,以输入流返回
//     *
//     * @param ftpPath FTP服务器上文件相对路径，例如：test/123
//     * @return Map<String, InputStream> 其中key为文件名，value为输入流对象
//     */
//    public Map<String, InputStream> getFileInputStream(String ftpPath) {
//        // 登录
//        Map<String, InputStream> map = new HashMap<String, InputStream>();
//        if (ftpClient != null) {
//            try {
//                String path = changeEncoding(basePath + ftpPath);
//                // 判断是否存在该目录
//                if (!ftpClient.changeWorkingDirectory(path)) {
//                    log.error(basePath + ftpPath + "该目录不存在");
//                    return map;
//                }
//                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
//                String[] fs = ftpClient.listNames();
//                // 判断该目录下是否有文件
//                if (fs == null || fs.length == 0) {
//                    log.error(basePath + ftpPath + "该目录下没有文件");
//                    return map;
//                }
//                for (String ff : fs) {
//                    String ftpName = new String(ff.getBytes(CHARSET_UTF8), CHARSET_UTF8);
//                    if (isDatFile(ftpName)) {
//                        InputStream is = ftpClient.retrieveFileStream(ff);
//                        map.put(ftpName, is);
//                        ftpClient.completePendingCommand(); // 处理多个文件
//                    }
//                }
//            } catch (IOException e) {
//                log.error("获取文件失败", e);
//            } finally {
//                Boolean close = closeConnect();
//                log.info("连接是否关闭：" + close);
//            }
//        }
//        return map;
//    }
//
//    /**
//     * 根据名称获取文件，以输入流返回
//     *
//     * @param ftpPath  FTP服务器上文件相对路径，例如：test/123
//     * @param fileName 文件名，例如：test.txt
//     * @return InputStream 输入流对象
//     */
//    public  InputStream getInputStreamByName(String ftpPath, String fileName) {
//        // 登录
//        InputStream input = null;
//        if (ftpClient != null) {
//            try {
//                String path = changeEncoding(basePath + ftpPath);
//                // 判断是否存在该目录
//                if (!ftpClient.changeWorkingDirectory(path)) {
//                    log.error(basePath + ftpPath + "该目录不存在");
//                    return null;
//                }
//                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
//                String[] fs = ftpClient.listNames();
//                // 判断该目录下是否有文件
//                if (fs == null || fs.length == 0) {
//                    log.error(basePath + ftpPath + "该目录下没有文件");
//                    return null;
//                }
//                for (String ff : fs) {
//                    String ftpName = new String(ff.getBytes(CHARSET_UTF8), CHARSET_UTF8);
//                    if (isDatFile(ftpName)) {
//                        int index = ftpName.indexOf(fileName);
//                        if (index != -1) {
//                            input = ftpClient.retrieveFileStream(ff);
//                            break;
//                        }
//                    }
//                }
//            } catch (IOException e) {
//                log.error("获取文件失败", e);
//            } finally {
//                Boolean connect = closeConnect();
//                log.info("连接关闭状态：" + connect);
//            }
//        }
//        return input;
//    }
//
//    /**
//     * 根据文件夹，文件 名称，判断是否存在
//     *
//     * @param ftpPath  FTP服务器上文件相对路径，例如：test/123
//     * @param fileName 文件名，例如：test.txt
//     * @return map
//     */
//    public Map checkoutFtpPathAndFileName(String ftpPath, String fileName) {
//        // 登录
//        Map<String, Boolean> map = new HashMap<String, Boolean>();
//        map.put("filePath", false);
//        map.put("fileName", false);
//        if (ftpClient != null) {
//            try {
//                String path = changeEncoding(basePath + ftpPath);
//                // 判断是否存在该目录
//                if (!ftpClient.changeWorkingDirectory(path)) {
//                    log.info(basePath + ftpPath + "该目录不存在");
//                    map.put("filePath", false);
//                } else {
//                    map.put("filePath", true);
//                }
//                ftpClient.enterLocalPassiveMode();  // 设置被动模式，开通一个端口来传输数据
//                String[] fs = ftpClient.listNames();
//                // 判断该目录下是否有文件
//                if (fs == null || fs.length == 0) {
//                    log.info(basePath + ftpPath + "该目录下没有文件");
//                    map.put("fileName", false);
//                }
//                for (String ff : fs) {
//                    String ftpName = new String(ff.getBytes(CHARSET_UTF8), CHARSET_UTF8);
//                    if (ftpName.equals(fileName)) {
//                        map.put("fileName", true);
//                    }
//                }
//            } catch (IOException e) {
//                log.error("获取文件失败", e);
//            }
//        }
//        return map;
//    }
//
//    /**
//     * 连接FTP服务器
//     *
//     * @param address  地址，如：127.0.0.1
//     * @param port     端口，如：21
//     * @param username 用户名，如：root
//     * @param password 密码，如：root
//     */
//    private Boolean login(String address, int port, String username, String password) {
//        ftpClient = new FTPClient();
//        try {
//            ftpClient.connect(address, port);
//            ftpClient.login(username, password);
//            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
//            int reply = ftpClient.getReplyCode();
//            if (!FTPReply.isPositiveCompletion(reply)) {
//                closeConnect();
//                log.error("FTP服务器连接失败：" + "地址：" + address + "  端口：" + port + "  用户名：" + username + "  密码：" + password);
//            } else {
//                b = true;
//            }
//        } catch (Exception e) {
//            log.error("FTP登录失败", e);
//        }
//        return b;
//    }
//
//    /**
//     * 关闭FTP连接
//     */
//    public Boolean closeConnect() {
//        Boolean b = false;
//        if (ftpClient != null && ftpClient.isConnected()) {
//            try {
//                ftpClient.logout();
//                b = true;
//            } catch (IOException e) {
//                log.error("关闭FTP连接失败", e);
//            }
//        }
//        return b;
//    }
//
//    /**
//     * FTP服务器路径编码转换
//     *
//     * @param ftpPath FTP服务器路径
//     * @return String
//     */
//    private static String changeEncoding(String ftpPath) {
//        String directory = null;
//        try {
//            if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(OPTS_UTF8, "ON"))) {
//                localCharset = CHARSET_UTF8;
//            }
//            directory = new String(ftpPath.getBytes(CHARSET_UTF8), CHARSET_UTF8);
//        } catch (Exception e) {
//            log.error("路径编码转换失败", e);
//        }
//        return directory;
//    }
//
//    private boolean isDatFile(String fileName) {
//        if (StringUtils.isNotBlank(fileName)) {
//            int length = fileName.length();
//            int index = fileName.indexOf(".");
//            if (index == -1) {
//                return false;
//            }
//            if (StringUtils.equals(fileName.substring(index + 1, length), "dat")) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
