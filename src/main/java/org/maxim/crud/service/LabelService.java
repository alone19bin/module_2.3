package org.maxim.crud.service;




import org.maxim.crud.model.Label;
import org.maxim.crud.repository.LabelRepository;
import org.maxim.crud.repository.hiber.LabelHib;

import java.util.List;

public class LabelService {
    private final LabelRepository labelRepository;

    public LabelService() {
        labelRepository = new LabelHib();
    }

    public LabelService(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    public Label save(Label labelToSave) {
        return labelRepository.save(labelToSave);
    }

    public List<Label> getAll() {
        return labelRepository.getAll();
    }

    public Label getById(int id) {
        return labelRepository.getById(id);
    }

    public Label update(Label label) {
        return labelRepository.update(label);
    }

    public boolean deleteById(int id) {
        return labelRepository.deleteById(id);
    }
}
