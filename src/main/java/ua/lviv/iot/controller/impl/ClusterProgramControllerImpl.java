package ua.lviv.iot.controller.impl;

import ua.lviv.iot.model.ClusterProgram;
import ua.lviv.iot.service.impl.ClusterProgramServiceImpl;

public class ClusterProgramControllerImpl extends AbstractControllerImpl<ClusterProgram, Integer> {
    public ClusterProgramControllerImpl() {
        super(new ClusterProgramServiceImpl());
    }
}
