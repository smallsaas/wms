import React, { useState } from 'react';
import { useDidMount, useWillUnmount, useForceUpdate } from 'zero-element/lib/utils/hooks/lifeCycle';
import useBreadcrumb from '@/framework/useBreadcrumb';
import ConfigItems from '@/pages/devops/configItems';
import ConfigGroups from '@/pages/devops/configGroups';
import { Tabs } from 'antd';
import Content from '@/layouts/Content';

const { TabPane } = Tabs;

export default function () {

  useBreadcrumb([
    { title: '首页', path: '' },
    { title: '配置管理' },
  ]);

  const [cKey, setCKey] = useState('configItems');

  useDidMount(_ => {
    const currentKey = localStorage.getItem("cConfigKey"); 
    if(currentKey){
      setCKey(currentKey);
    }
  });

  useWillUnmount(_ => {
  });

  //permpage, permgroups
  function handleChangeTabPane(key) {
    localStorage.setItem("cConfigKey", key); 
    setCKey(key);
  }

  return (
    <Content>
      <Tabs
        destroyInactiveTabPane
        onChange={handleChangeTabPane}
        activeKey={cKey}
      >
        <TabPane tab="配置项" key="configItems">
          <ConfigItems />
        </TabPane>
        <TabPane tab="配置分组" key="configgroups">
          <ConfigGroups />
        </TabPane>
      </Tabs>
    </Content>
  )
}