import React, { useState } from 'react';
import ZEle from 'zero-element';
import { history } from 'umi';
import useBreadcrumb from '@/framework/useBreadcrumb';
import { get as getEndpoint } from 'zero-element/lib/utils/request/endpoint';

import { viewConf, logsConf, warehouseOutConf } from './config/sales-view';

import { Tabs, Button, Space } from 'antd'

export default function (props) {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '详情' },
      ]);

    const { id, salesStatus } = props.location.query;
    const [ tabIndex, setTabIndex ] = useState('1')
    

    //显示日志
    const ShowLogs = () => {
      logsConf.items[1].config.API.listAPI = `/api/logs?targetType=distributor_out&targetId=${id}`
      // setLogStatus(true)
      return <ZEle namespace="sales-logs" config={logsConf} />
    }
    
    //获取tab index
    const onTabClick = (index) => {
      setTabIndex(`${index}`)
    }

    //打印
    const print = () => {
      const link = document.createElement('a');
      link.target = '_blank';
      link.href = `${getEndpoint()}/api/pub/cloud/io/pdf/out/distributor-order?identifier=${id}&key=94a08da1fecbb6e8b46990538c7b50b2`;
      link.click();
    }

    //返回上一页
    function back(){
      history.goBack()
    }

    function showWarehousOutForm () {
      history.push({
        pathname: "/sales/sales-warehouseOut",
        query: {
          id: id
        }
      })
    }
    
    //右边按钮
    const operations = <Space>
      { tabIndex === '1' ?  <Button type="primary" onClick={print}>打印</Button> : null }
      { tabIndex === '2' && ( salesStatus === 'WaitForStorageOut' || salesStatus === 'SectionStorageOut') ? <Button type='primary' onClick={() => showWarehousOutForm()} >出库</Button> : null}
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
          <Tabs.TabPane tab="分销单详情" key="1">
            <ZEle namespace="sales-view" config={viewConf} />
            
            { tabIndex === "1" ? (
              <ShowLogs/>
            ): <></>}
          </Tabs.TabPane>
          <Tabs.TabPane tab="商品出库" key="2">
            <ZEle namespace="sales-history-view" config={warehouseOutConf} />
          </Tabs.TabPane>
        </Tabs>
      </div>
      
    )
  }