package it.uniroma2.dicii.ispw.gradely;

public abstract class AbstractGraphicControl {

    protected BaseGraphicControl parentControl;

    public void setParentControl(BaseGraphicControl parentControl) {
        this.parentControl = parentControl;
    }
}
