package com.demo.event;

import org.springframework.context.ApplicationEvent;

/**
 * @date 2022-9-22
 **/
public class TestEvent extends ApplicationEvent {

    private String msg;

    public TestEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
