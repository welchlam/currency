ALTER TABLE CTLM_ACTION ADD (
  CONSTRAINT FK_CTLM_ACTION_1
  FOREIGN KEY (JOB_NAME) 
  REFERENCES CTLM_JOB(JOBNAME)
  ENABLE VALIDATE);
