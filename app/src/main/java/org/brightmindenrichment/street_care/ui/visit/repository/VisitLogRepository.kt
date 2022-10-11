package org.brightmindenrichment.street_care.ui.visit.repository

import org.brightmindenrichment.street_care.ui.visit.data.VisitLog

interface VisitLogRepository {
    fun addVisitLog(visitLog : VisitLog)

}