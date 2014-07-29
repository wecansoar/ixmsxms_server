package net.flower.ixmsxms_server.dao;

import net.flower.ixmsxms_server.domain.Notice;
import net.flower.ixmsxms_server.domain.Program;

import java.util.List;

@Master
public interface ProgramDao {
    public Program select();
}