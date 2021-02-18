package com.example.visualp.system213.facade;

import com.example.visualp.system213.dao.SampleDAO;
import com.example.visualp.system213.dto.SampleDTO;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class StudyFacade {

  @Inject
  private SampleDAO dao;

  @Nullable
  public SampleDTO read(int id) throws Exception {
    return dao.read(id);
  }

  @Nonnull
  public SampleDTO update() throws Exception {
    return null;
  }
}
