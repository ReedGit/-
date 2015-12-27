#BaseActivity类的使用说明
---
##简介

BaseActivity类封装了一些关于Android 6.0上权限管理的使用方法，尽可能减少开发过程中代码的冗余。

- Map<String, Boolean> permissionNeeded(Activity activity, String[] permissions)：次方法用于判断所需要权限中有哪些是已经获得的，哪些需要用户手动赋予的；同时在这些需要手动赋予的权限中哪些是可以是利用对话框弹出提示以说明权限的使用目的。最后将所有需要用户手动赋予的权限以map的形式返回，map的key是权限名称，value是true（不需要进行权限说明提示）或者false（可以进行权限说明提示）
- void showRequestMessage(Context context, String message, final String[] permissions, final int requestCode)：此方法用于向用户展示权限说明提示，参数中permissions是所有应用程序没有获得但需要用户赋予的权限，requestCode是请求权限时的请求码。
- List< String> permissionShow(Map<String, Boolean> permissionMap)：此方法是用于找出permissionMap中哪些权限是需要提示说明的。返回值是需要提示说明的权限的集合。
- abstract void grantedPermission(String[] permissions, int requestCode)：此方法需要继承，用于用户了解权限说明后程序进行的后续操作，一般是调用请求权限方法。

##使用

1. 创建一个Activity继承BaseActivity
2. 实现grantedPermission(String[] permissions, int requestCode)方法，一般在该方法内调用ActivityCompat.requestPermissions(...)
3. 实现onRequestPermissionsResult(...)方法，用于在接受权限请求回调后根据请求结果进行后续操作
4. 在需要请求权限时调用permissionNeeded(...)，利用返回结果进行相应后续处理

可参考MainActivity.java中的代码