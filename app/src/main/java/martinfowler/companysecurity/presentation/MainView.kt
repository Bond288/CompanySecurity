package martinfowler.companysecurity.presentation

interface MainView {
    fun toggleState(isLocked: Boolean)

    fun password(): String

    fun showPassword()

    fun initState(disabled: Boolean)
}