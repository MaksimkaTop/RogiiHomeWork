package data

import javax.xml.crypto.Data


class ResourcesListData {
    var page = 0
    var per_page = 0
    var total = 0
    var total_pages = 0
    var data: ArrayList<ResourcesData>? = null
    var support: Support? = null
}

class SingleResource {
    var data: ResourcesData? = null
    var support: Support? = null
}

class ResourcesData {
    var id = 0
    var name: String? = null
    var year = 0
    var color: String? = null
    var pantone_value: String? = null
}


