# android studio建立aidl #
在module的build.gradle文件里添加一下代码:
```Go
android{
	......
	......
	sourceSets {
	    main {
	        aidl.srcDirs = ['src/main/java']
	    }
	}
	......
	......
}
```
这样就可以在src/main/java目录下创建aidl文件