ALTER TABLE CTLM_OUT_CONDITION ADD (
  CONSTRAINT FK_CTLM_OUT_CONDITION_2
  FOREIGN KEY (PARENT_JOB) 
  REFERENCES CTLM_JOB(JOBNAME)
  ENABLE VALIDATE);
