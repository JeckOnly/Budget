package com.jeckonly.util

import timber.log.Timber

class FileClassMethodTag: Timber.DebugTree(){
    override fun createStackElementTag(e: StackTraceElement): String {
        return "(${e.fileName}:${e.lineNumber})[${e.className}][${e.methodName}]"
    }
}