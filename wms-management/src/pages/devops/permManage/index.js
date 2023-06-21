import useBreadcrumb from '@/framework/useBreadcrumb';
import React, { useState } from 'react';
import { useDidMount, useWillUnmount, useForceUpdate } from 'zero-element/lib/utils/hooks/lifeCycle';
import { Tabs } from 'antd';
import Content from '@/layouts/Content';
import Perm from '@/pages/devops/perm';
import PermGroups from '@/pages/devops/permGroups';
import PermDefine from '@/pages/devops/permDefine';

const { TabPane } = Tabs;

export default function () {

  useBreadcrumb([
    { title: '首页', path: '' },
    { title: '权限管理' },
  ]);

  
  const [cKey, setCKey] = useState('permpage');

  useDidMount(_ => {
    const currentKey = localStorage.getItem("cPermKey"); 
    if(currentKey){
      setCKey(currentKey);
    }
  });

  useWillUnmount(_ => {
  });

  //permpage, permgroups
  function handleChangeTabPane(key) {
    localStorage.setItem("cPermKey", key); 
    setCKey(key);
  }

  return (
    <Content>
      <Tabs
        destroyInactiveTabPane
        onChange={handleChangeTabPane}
        activeKey={cKey}
      >
        <TabPane tab="权限管理" key={`permpage`}>
          <Perm />
        </TabPane>
        <TabPane tab="权限分组管理" key={`permgroups`}>
          <PermGroups />
        </TabPane>
        <TabPane tab="权限定义列表" key={`permDefine`}>
          <PermDefine />
        </TabPane>
      </Tabs>
    </Content>
  )
}