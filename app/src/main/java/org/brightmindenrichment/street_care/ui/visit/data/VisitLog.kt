package org.brightmindenrichment.street_care.ui.visit.data

import java.util.*

 class VisitLog {

    var location: String? = null
    var date: Date = Calendar.getInstance().time
    var hours: Long? = null
    var visitAgain: String? = null
    var peopleCount: Long? = null
    var experience: String? = null
    var comments: String? = null

}