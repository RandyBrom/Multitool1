package menu

import ru.juraogurcov.multitool.R

sealed class BottomItem(val resId: Int, val titleId: Int, val route: String) {
    object FirstScreen : BottomItem(R.drawable.ic_person_button, R.string.person_btn, "FirstScreen")
    object SecondScreen : BottomItem(R.drawable.ic_vpn_button, R.string.vpn_btn, "SecondScreen")
    object ThirdScreen : BottomItem(R.drawable.ic_more, R.string.more_btn, "ThirdScreen")
}
