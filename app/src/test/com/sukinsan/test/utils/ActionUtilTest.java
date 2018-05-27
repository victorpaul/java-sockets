package com.sukinsan.test.utils;

import com.sukinsan.utils.ActionUtil;
import com.sukinsan.utils.ActionUtilImpl;
import org.junit.Test;

public class ActionUtilTest {

    @Test
    public void check_key_press(){
        int space = 32;
        ActionUtil au = new ActionUtilImpl();
//99
        au.receiveAction(ActionUtil.ActionType.KEYBOARD_PRESSED + ActionUtilImpl.actionMessageSeparator + space);
        au.receiveAction(ActionUtil.ActionType.KEYBOARD_PRESSED + ActionUtilImpl.actionMessageSeparator + space);
        au.receiveAction(ActionUtil.ActionType.KEYBOARD_PRESSED + ActionUtilImpl.actionMessageSeparator + space);
    }

}
