import React, { PureComponent, Fragment } from 'react';

import QueryComponents from '../../components/query';

export default class Query extends PureComponent {
  componentDidMount() {
    const { requester } = this.props;
    requester.fetchOne();
  }

  render() {
    return (
      <QueryComponents {...this.props} queryConfig={{
        title: '盘点详情',
        itemsField: 'skuRecords',
        info: [
          { label: '盘点单编号', field: 'checkCode' },
          { label: '仓库', field: 'warehouseName' },
          { label: '经办人', field: 'transactionBy' },
          { label: '盘点时间', field: 'checkTime' },
          { label: '备注', field: 'checkNote' },
          { label: '制单人', field: 'originatorName' },
        ],
        columns: [
          { title: ' ', field: 'total' },
          { title: '商品条码', field: 'skuBarcode' },
          { title: '商品编号', field: 'skuCode' },
          { title: '商品名称', field: 'skuName' },
          { title: '需求数量', field: 'demandQuantities', align: 'right', bottomTotal: true },
          { title: '盘点前数量', field: 'beforeProofQuantities', align: 'right', bottomTotal: true },
          { title: '盘点后数量', field: 'factQuantities', align: 'right', bottomTotal: true },
          {
            title: '盈亏数量', field: 'profitLost', align: 'right', bottomTotal: true,
            valueType: 'profitAndLoss', options : {
              fixed: false,
            }
          },
          { title: '单位', field: 'skuUnit' },
        ]
      }}
        dispatch={this.props.dispatch}
        namespace={this.props.namespace}
        location={this.props.location}
        statusField="status"
        API="/api/wms/checks"
        logType="check"
      />
    );
  }
}