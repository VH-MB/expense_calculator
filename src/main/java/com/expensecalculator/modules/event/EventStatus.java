package com.expensecalculator.modules.event;

enum EventStatus {

    SETTLED("settled") {
        @Override
        public void getInfo() {
            System.out.println("");
        }

    },

    NOV_SETTLED("nov settled") {
        @Override
        public void getInfo() {
            System.out.println("");
        }
    };

    private String status;

    EventStatus(String status) {
        this.status = status;
    }

    public abstract void getInfo();

}
