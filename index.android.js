/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';

import ScannerModule from './ScannerModule';

import {
  AppRegistry,
  StyleSheet,
  Text,
  View
} from 'react-native';

class AwesomeProject extends Component {
  _onPressButton() {

    ScannerModule.startActivityForResult(
      "cc.libqrscanner.ScannerActivity",100,
      (successMsg) => {
            ScannerModule.show( successMsg, ScannerModule.SHORT);
      },
      (erroMsg) => {alert(erroMsg)}
      );
  }

  render() {
    return (
        <Text onPress={this._onPressButton}>点击我去扫描</Text>
    );
  }



}


AppRegistry.registerComponent('AwesomeProject', () => AwesomeProject);
