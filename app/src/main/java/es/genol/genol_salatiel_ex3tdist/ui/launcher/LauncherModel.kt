package es.genol.genol_salatiel_ex3tdist.ui.launcher

sealed class LauncherModel(val path: String) {
    object ExerciseList: LauncherModel("ExerciseList")
    object ExerciseOne: LauncherModel("ExerciseOne")
    object ExerciseTwo: LauncherModel("ExerciseTwo")
    object ExerciseThree: LauncherModel("ExerciseThree")
}