package com.example.visualp.system002.facade;

import javax.annotation.Nonnull;

public interface StudyFacade {

  void studyRead(@Nonnull String id) throws Exception;

  void studyCount(@Nonnull String id) throws Exception;

  void batchWrite(@Nonnull String id) throws Exception;

  void studyAdd();
}
