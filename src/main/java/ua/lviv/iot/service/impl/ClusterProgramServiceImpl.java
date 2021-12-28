package ua.lviv.iot.service.impl;

import ua.lviv.iot.dao.impl.ClusterProgramDaoImpl;
import ua.lviv.iot.model.ClusterProgram;

public class ClusterProgramServiceImpl extends AbstractServiceImpl<ClusterProgram, Integer> {
    public ClusterProgramServiceImpl() {
        super(new ClusterProgramDaoImpl());
    }
}
