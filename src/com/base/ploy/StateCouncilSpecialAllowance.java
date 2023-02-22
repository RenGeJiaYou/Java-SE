package com.base.ploy;

//国务院特殊津贴，这部分收入免税.如果只是包内的Main 类调用的话，可以不加 public
class StateCouncilSpecialAllowance extends Income {
    public StateCouncilSpecialAllowance(double income) {
        super(income);
    }

    @Override
    public double getTax() {
        return 0;
    }
}
