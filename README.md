# react-native-qrcode `only for Android`


## 运行
工程根目录，插上android手机，执行 `react-native run-android `


## 移植到自己的工程中

1. 复制 `android/libqrscanner` 文件加到你的工程目录下，默认与`android/app`同级目录。
2. 在你的工程 android/setting.gradle 里加入

         include ':app', ':libqrscanner'
  
3. 在`android/app/build.gradle`里加入

        dependencies {
            compile fileTree(dir: "libs", include: ["*.jar"])
            ＊＊＊
            compile project(':libqrscanner')
        }

4. 在`getPackages`中加入`new QRScannerReactPackage()`

        @Override
        protected List<ReactPackage> getPackages() {
          return Arrays.<ReactPackage>asList(
              new MainReactPackage(),new QRScannerReactPackage()
          );
        }

5. 在需要调用相机的地方引入如下代码，例如`index.android.js`

        ScannerModule.startActivityForResult(
            "cc.libqrscanner.ScannerActivity",100,
            (successMsg) => {
                  ScannerModule.show( successMsg, ScannerModule.SHORT);
            },
            (erroMsg) => {alert(erroMsg)}
            );
        }

> ScannerModule.show 是toast输出结果



### 参考资料
>  http://blog.csdn.net/qq_22780533/article/details/51907506
