package com.libdumper

import java.io.File

object Fixer {

    fun fixDump(Exec: String, path: File, memory: Memory): String {
        val param = arrayOf(
            "$Exec${File.separator}libfixer.so",
            path.absolutePath,
            "${memory.sAddress}",
            "/sdcard/Download/${getName(path)}_fix${getFileExtension(path)}"
        )
        val proc = Runtime.getRuntime().exec(param)
        proc.waitFor()
        val res = proc.inputStream.bufferedReader().readLines()
        proc.destroy()
        return res.joinToString("\n")
    }

    private fun getName(file: File): String {
        var fileName = ""
        try {
            if (file.exists()) {
                val name = file.name
                fileName = name.replaceFirst("[.][^.]+$".toRegex(), "")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            fileName = ""
        }
        return fileName
    }

    private fun getFileExtension(file: File): String {
        val name = file.name
        val lastIndexOf = name.lastIndexOf(".")
        return if (lastIndexOf == -1) {
            "" // empty extension
        } else name.substring(lastIndexOf)
    }
}