ALTER TABLE CTLM_IN_CONDITION ADD (
  CONSTRAINT FK_CTLM_IN_CONDITION_1
  FOREIGN KEY (JOB_NAME) 
  REFERENCES CTLM_JOB(JOBNAME)
  ENABLE VALIDATE);
