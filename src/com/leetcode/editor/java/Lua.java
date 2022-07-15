package com.leetcode.editor.java;

/**
 * @Author: HaiDi
 * @Date: 2022/3/11 17:08
 */
public class Lua {
    public void luatest() {
        String lua = "eval \"local accountStr = redis.call('get', KEYS[1]) if not accountStr then return -1 end local account = cjson.decode(accountStr) if ('stopAdd' == account['accountStatus']  or 'stopAddSubtract' == account['accountStatus'] or 'disable' == account['accountStatus'])  and KEYS[2]+0 > 0 then return -2 end if ('stopSubtract' == account['accountStatus']  or 'stopAddSubtract' == account['accountStatus'] or 'disable' == account['accountStatus']) and KEYS[2]+0 < 0 then return -3 end local afterAvailableBalance = account['availableBalance'] + KEYS[2] local afterBalance = account['balance'] + KEYS[2] if afterAvailableBalance < 0 then return -4 end if account['sign'] ~= redis.sha1hex(account['balance']) then return -5 end local timeStr = redis.call('TIME')[1] local balanceSign = redis.sha1hex(afterBalance) account['availableBalance'] = afterAvailableBalance account['balance'] = afterBalance account['sign'] = balanceSign account['version'] = account['version'] + 1 account['accountStatus'] = KEYS[3] local afterAccountJson = cjson.encode(account) redis.call('set', KEYS[1], afterAccountJson) redis.call('set', KEYS[4], timeStr) return afterAccountJson \" 3  P4_M2022031002_CASHBACK 0 stopSubtract";
    }
}
