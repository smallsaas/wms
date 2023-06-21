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
    history.push('/login');
  }
  function routeAuth() {
    history.push('/profile/auth');
  }

  return <Content style={{ background: '#fff' }}>
    <Result
      title="账号正在审核"
      subTitle="您可以完善信息, 稍后再次尝试登录; 或使用其它账号登录"
      extra={[
        <Button key="auth" onClick={routeAuth}>完善信息</Button>,
        <Button key="login" onClick={routeLogin}>返回登录</Button>,
      ]}
    >
    </Result>
  </Content>
}