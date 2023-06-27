import React, { useState } from 'react';
import ZEle from 'zero-element';
import { history } from 'umi';
import useBreadcrumb from '@/framework/useBreadcrumb';
import { useDidMount, useWillUnmount, useForceUpdate } from 'zero-element/lib/utils/hooks/lifeCycle';

import { viewConf, logsConf } from './config/procurements-view';
import historyConf from './config/procurements-inHistories-view';

import { Tabs, Button, Space } from 'antd'

export default function (props) {
    useBreadcrumb([
        { title: '主页', path: '/' },
        { title: '详情' },
      ]);

    const { id, procureStatus } = props.location.query;
    const [ tabIndex, setTabIndex ] = useState('1')
    const [ logStatus, setLogStatus ] = useState(false)
    

    //显示日志
    const showLogs = () => {
      logsConf.items[1].config.API.listAPI = `/api/logs?targetType=purchase&targetId=${id}`
      setLogStatus(true)
    }
    
    //获取tab index
    const onTabClick = (index) => {
      setTabIndex(`${index}`)
    }

    //返回上一页
    function back(){
      history.goBack()
    }

    function showWarehousingForm () {
      history.push({
        pathname: "/procurements/procurements-warehousing",
        query: {
          id: id
        }
      })
    }
    
    //右边按钮
    const operations = <Space>
      { tabIndex === '1' ? <Button type='primary' onClick={()=>showLogs()} >日志记录</Button> : null}
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
            
            { tabIndex === "1" && logStatus ? (
              <ZEle namespace="procurements-logs" config={logsConf} />
            ): <></>}
          </Tabs.TabPane>
          <Tabs.TabPane tab="商品入库" key="2">
            <ZEle namespace="procurements-history-view" config={historyConf} />
          </Tabs.TabPane>
        </Tabs>
      </div>
      
    )
  }