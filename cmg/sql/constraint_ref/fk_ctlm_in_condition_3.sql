ALTER TABLE CTLM_IN_CONDITION ADD (
  CONSTRAINT FK_CTLM_IN_CONDITION_3
  FOREIGN KEY (CHILD_JOB) 
  REFERENCES CTLM_JOB(JOBNAME)
  ENABLE VALIDATE);