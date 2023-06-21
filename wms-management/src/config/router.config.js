module.exports = [
  // {
  //   name: '普通菜单',
  //   path: '/a',
  // },
  // {
  //   name: '分割线',
  //   key: 'divider',
  // },
  // {
  //   name: '一级菜单',
  //   path: '/b',
  //   icon: '<svg viewBox=0 0 1024 1024 version=1.1 xmlns=http://www.w3.org/2000/svg><path d=M903.168 998.4c-25.6 0-46.592-19.968-48.128-45.568a343.808 343.808 0 0 0-343.04-323.584c-181.76 0-332.288 142.336-343.04 323.584-1.536 26.624-24.576 46.592-50.688 45.568a48.4352 48.4352 0 0 1-45.568-50.688c13.312-232.448 206.336-414.72 439.296-414.72s425.984 182.272 439.296 414.72a48.128 48.128 0 0 1-45.568 50.688h-2.56z fill= p-id=1188></path><path d=M512 629.76a302.592 302.592 0 0 1-302.08-302.08C209.92 161.28 345.6 25.6 512 25.6c166.4 0 302.08 135.68 302.08 302.08 0 166.4-135.68 302.08-302.08 302.08z m0-507.904c-113.152 0-205.824 92.16-205.824 205.824a205.824 205.824 0 0 0 411.648 0c0-113.664-92.672-205.824-205.824-205.824z></path></svg>',
  //   items: [
  //     {
  //       path: '/b/c',
  //       name: '二级菜单A',
  //     },
  //     {
  //       path: '/b/d',
  //       name: '二级菜单B',
  //     },
  //   ],
  // },
  // {
  //   name: '系统管理',
  //   path: '/sys',
  //   // permissions: [
  //   //   'Org.view',
  //   //   'sysUser.view',
  //   //   'sysRole.view',
  //   //   'Config.view',
  //   //   'OperationLog.view'
  //   // ],
  //   items: [
  //     {
  //       name: '菜单管理',
  //       path: '/sys/menuManage',
  //     },
  //     {
  //       name: '组织管理',
  //       path: '/sys/org',
  //       permissions: ''
  //     },
  //     {
  //       name: '用户管理',
  //       path: '/sys/user',
  //       permissions: ''
  //     },
  //     {
  //       name: '角色管理',
  //       path: '/sys/role',
  //       permissions: ''
  //     },
  //     {
  //       name: '系统配置',
  //       path: '/sys/setting',
  //       permissions: ''
  //     },
  //     {
  //       name: '操作日志',
  //       path: '/sys/logs',
  //       permissions: ''
  //     }
  //   ]
  // },
  // {
  //   "name": "运维管理",
  //   "path": "/devops",
  //   "items": [
  //     {
  //       "name": "数据现场管理",
  //       "path": "/devops/backup"
  //     },
  //     {
  //       path: '/devops/tables',
  //       name: '报表管理',
  //     }
  //     // {
  //     //   "name": "问题跟踪",
  //     //   "path": "/devops/issueTask"
  //     // },
  //     // {
  //     //   "name": "微服务权限配置",
  //     //   "path": "/devops/appPermission"
  //     // },
  // {
  //   name: '配置管理',
  //   path: '/devops/configManage'
  // },
  // {
  //   name: '权限管理',
  //   path: '/devops/permManage'
  // }
  //   ]
  // }, 


  // {
  //   name: '产品管理',
  //   path: '/product',
  //   items: [
  //     {
  //       path: '/product/categroy',
  //       name: '产品类别',
  //     },
  //     {
  //       path: '/product/items',
  //       name: '产品',
  //     },
  //     {
  //       path: '/product/tag',
  //       name: '产品标签',
  //     },
  //     // {
  //     //   path: '/product/trial',
  //     //   name: '试用装管理',
  //     //   icon: 'tag',
  //     // },
  //     // {
  //     //   path: '/product/evaluate',
  //     //   name: '评价管理',
  //     //   icon: 'tag',
  //     // },
  //     // {
  //     //   path: '/product/freight',
  //     //   name: '运费模板',
  //     // },
  //     // {
  //     //   path: '/product/brand',
  //     //   name: '品牌管理',
  //     // }
  //   ]
  // },

  {
    name: "库存盘点管理",
    path: "/checks"
  },
  {
    name: "仓库库存管理",
    path: "/inventories"
  },
  {
    name: "采购单管理",
    path: "/procurements"
  },
  {
    name: "采购退货管理",
    path: "/refunds"
  },
  {
    name: "分销单管理",
    path: "/sales"
  },
  {
    name: "商品分类管理",
    path: "/productCategories"
  },
  {
    name: "商品库存管理",
    path: "/skus"
  },
  {
    name: "商品单位管理",
    path: "/units"
  }

]