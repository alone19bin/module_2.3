package org.maxim.crud.service;

import org.maxim.crud.repository.hiber.LabelHib;
import org.maxim.crud.model.Label;


import java.util.List;

public class LabelService {
    private final LabelHib labelHib;

    public LabelService(LabelHib labelDao) {
        this.labelHib = labelDao;
    }

    public LabelService() {
        this.labelHib = new LabelHib();
    }

    public List<Label> getLabels() {
        return labelHib.getLabels();
    }
    public Label getLabel(Integer labelId) {
        return labelHib.getLabelById(labelId);
    }
    public Label saveLabel(Label label) {
        return labelHib.saveLabel(label);
    }
    public Label update (Label label) {
        return labelHib.update(label);
    }
    public void deleteById(Integer integer) {
        labelHib.deleteById(integer);
    }
}
