CREATE OR REPLACE VIEW CTLM_V_SYSOUT
(
  ACTION_DETAIL_ID
, SYSOUT_OPTION
, PAR
)
AS
SELECT
  ACTION_DETAIL_ID
, SYSOUT_OPTION
, PAR
FROM CTLM_SYSOUT
;