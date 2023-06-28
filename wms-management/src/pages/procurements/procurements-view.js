import React, { useState } from 'react';
import ZEle from 'zero-element';
import { history } from 'umi';
import useBreadcrumb from '@/framework/useBreadcrumb';
import { get as getEndpoint } from 'zero-element/lib/utils/request/endpoint';

import { viewConf, logsConf, warehousingConf } from './config/procurements-view';

import { Tabs, Button, Space } from 'antd'

export default function (props) {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '详情' },
      ]);

    const { id, procureStatus } = props.location.query;
    const [ tabIndex, setTabIndex ] = useState('1')
    

    //显示日志
    const ShowLogs = () => {
      logsConf.items[1].config.API.listAPI = `/api/logs?targetType=purchase&targetId=${id}`
      return <ZEle namespace="procurements-logs" config={logsConf} />
    }
    
    //获取tab index
    const onTabClick = (index) => {
      setTabIndex(`${index}`)
    }

    //返回上一页
    function back(){
      history.goBack()
    }

    //跳转至入库操作页面
    function showWarehousingForm () {
      history.push({
        pathname: "/procurements/procurements-warehousing",
        query: {
          id: id
        }
      })
    }

    const print = () => {
      const link = document.createElement('a');
      link.target = '_blank';
      link.href = `${getEndpoint()}/api/pub/cloud/io/pdf/out/deliver?identifier=${id}&key=94a08da1fecbb6e8b46990538c7b50b2`;
      link.click();
  
    }
    const exportAction = () => {
      const link = document.createElement('a');
      link.target = '_blank';
      link.href = `${getEndpoint()}/api/pub/poi/agent/export/wms/procurement/${id}?key=94a08da1fecbb6e8b46990538c7b50b2`;
      link.click();
    }
    
    //右边按钮
    const operations = <Space>
      { tabIndex === '1' ? (
        <>
          <Button type="primary" onClick={exportAction}>导出</Button>
          <Button type="primary" onClick={print}>打印</Button>
        </>
      ):null}
      { tabIndex === '2' && ( procureStatus === 'Audit_Passed' || procureStatus === 'SectionStorageIn') ? <Button type='primary' onClick={() => showWarehousingForm()} >入库</Button> : null}
      <Button onClick={back}>返回</Button>
    </Space>

    return (
      <div style={{ 
        background: "#fff", 
        boxShadow: "0 2px 8px rgba(0, 0, 0, 0.15)",
        padding: "16px",
        minHeight: "100%",
        marginBottom: "16px"
        }}>
        <Tabs tabBarExtraContent={operations} onTabClick={onTabClick} defaultActiveKey={tabIndex}>
          <Tabs.TabPane tab="订单详情" key="1">
            <ZEle namespace="procurements-view" config={viewConf} />
            
            { tabIndex === "1" ? (
              <ShowLogs/>
            ): <></>}
          </Tabs.TabPane>
          <Tabs.TabPane tab="商品入库" key="2">
            <ZEle namespace="procurements-history-view" config={warehousingConf} />
          </Tabs.TabPane>
        </Tabs>
      </div>
      
    )
  }