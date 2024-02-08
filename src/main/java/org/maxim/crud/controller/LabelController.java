package org.maxim.crud.controller;

import lombok.RequiredArgsConstructor;
import org.maxim.crud.model.Label;
import org.maxim.crud.service.LabelService;

import java.util.List;

@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService = new LabelService();

    public Label createlabel(Label label) {
        return labelService.saveLabel(label);
    }
    public List<Label> getLabels() {
        return labelService.getLabels();
    }
    public Label getLabel(Integer labelId) {
        return labelService.getLabel(labelId);
    }
    public Label updateLabel(Label label) {
        return labelService.update(label);
    }
    public void deleteById(Integer integer) {
        labelService.deleteById(integer);
    }
}