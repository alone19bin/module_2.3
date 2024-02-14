package org.maxim.crud.controller;

import lombok.RequiredArgsConstructor;
import org.maxim.crud.model.Label;
import org.maxim.crud.model.Status;
import org.maxim.crud.repository.hiber.LabelHib;
import org.maxim.crud.service.LabelService;

import java.util.List;

@RequiredArgsConstructor
public class LabelController {
    private final LabelService labelService = new LabelService();

    public Label add(String labelName) {
        Label newLabel = new Label();
        newLabel.setName(labelName);
        newLabel.setStatus(Status.ACTIVE);
        return labelService.save(newLabel);
    }

    public List<Label> getAll() {
        return labelService.getAll();
    }

    public Label getById(int id) {
        return labelService.getById(id);
    }

    public Label update(Label label, String newName, boolean changeStatus) {
        boolean changeName = (newName != null && !newName.isEmpty());
        if (!changeName && !changeStatus) return label;
        if (changeName) label.setName(newName);
        if (changeStatus) {
            Status newStatus = (label.getStatus() == Status.DELETED) ? Status.ACTIVE : Status.DELETED;
            label.setStatus(newStatus);
        }
        return labelService.update(label);
    }

    public boolean deleteById(int id) {
        return labelService.deleteById(id);
    }
}