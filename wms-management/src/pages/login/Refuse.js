import React from 'react';
import { history } from 'umi';
import { Layout, Result, Button } from 'antd';
import useBreadcrumb from '@/framework/useBreadcrumb';

const { Content } = Layout;

export default function Oauth(props) {

  useBreadcrumb([
    { title: '首页', path: '/' },
    { title: '实名认证', path: '/profile/auth' },
    { title: '审核结果', },
  ]);

  function routeLogin() {
    history.push('/profile/auth');
  }

  return <Content style={{ background: '#fff' }}>
    <Result
      status="error"
      title="账号审核被拒绝"
      subTitle="您可以重新提交认证信息, 以便再次审核"
      extra={[
        <Button key="auth" onClick={routeLogin}>修改认证信息</Button>,
      ]}
    >
    </Result>
  </Content>
}