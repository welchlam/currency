ALTER TABLE CTLM_C_CONDITION ADD (
  CONSTRAINT FK_CTLM_C_CONDITION_1
  FOREIGN KEY (JOB_NAME) 
  REFERENCES CTLM_JOB(JOBNAME)
  ENABLE VALIDATE);
