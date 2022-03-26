package com.rightdirection.megapet.model.menu


import com.rightdirection.megapet.R

object MenuItemData {
    val functionList = listOf(
        MenuItem(R.drawable.ic_baseline_person_36,R.string.menu_title_account,R.string.menu_desc_account),
        MenuItem(R.drawable.ic_baseline_language_36,R.string.menu_title_language,R.string.menu_desc_language),
        MenuItem(R.drawable.ic_baseline_info_36,R.string.menu_title_version, R.string.app_version)
    )

    val  functionListWithLogin = listOf(
        MenuItem(R.drawable.ic_baseline_person_36,R.string.menu_title_account,R.string.menu_desc_account_is_login),
        MenuItem(R.drawable.ic_baseline_language_36,R.string.menu_title_language,R.string.menu_desc_language),
        MenuItem(R.drawable.ic_baseline_logout_36,R.string.menu_title_logout,R.string.menu_desc_logout),
        MenuItem(R.drawable.ic_baseline_info_36,R.string.menu_title_version, R.string.app_version)
    )


    //MenuItem(R.drawable.ic_baseline_favorite_border_36,"我的最愛","查看你的願望列表"),
    //MenuItem(R.drawable.ic_baseline_drive_eta_36,"送貨需知","查看送貨條款"),
    //MenuItem(R.drawable.ic_baseline_storefront_36,"店舖列表","查看各店鋪地址/電話"),

}