# 关于，须知，联系我们 等页面的配置内容




#修改历史:
    ##date:2019-04-04 16:30:30
    #1:ID为29的调拨单子项数据丢失，手动加入了数据，如下
    INSERT INTO `wms_storage_out_item` VALUES ('1000', '533', '1', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('1100', '533', '2', null, '100', '1000', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('1230', '533', '5', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('1231', '533', '4', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('1232', '533', '11', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('1233', '533', '17', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('1235', '533', '3', null, '100', '1000', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('1333', '533', '6', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('1556', '533', '18', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('2123', '533', '13', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('2310', '533', '10', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('2313', '533', '9', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('2315', '533', '8', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('3212', '533', '22', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('4532', '533', '14', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('5123', '533', '16', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('5655', '533', '7', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('6541', '533', '19', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');
    INSERT INTO `wms_storage_out_item` VALUES ('7897', '533', '21', null, '100', '900', '2019-02-28 11:48:22', 'TRF2019280200003', 'TRANSFER', '100');


    #2:fixed删除 采购单/退货单 问题

    #3: 插入的数据 ID 指向出错。修改 如下 10/04/2019
    update wms_storage_out_item set wms_storage_out_item.storage_out_id = 29 where wms_storage_out_item.id in (1000,                                                                                                            1100,
                                                                                                            1230,
                                                                                                            1231,
                                                                                                            1232,
                                                                                                            1233,
                                                                                                            1235,
                                                                                                            1333,
                                                                                                            1556,
                                                                                                            2123,
                                                                                                            2310,
                                                                                                            2313,
                                                                                                            2315,
                                                                                                            3212,
                                                                                                            4532,
                                                                                                            5123,
                                                                                                            5655,
                                                                                                            6541,
                                                                                                            7897)



    #4: bug 导致数据值出错，修复数据 19/04/2019
        ##出库子项相关:
        -- skuID=1
        update wms_storage_out_item set after_transaction_quantities = 899 WHERE wms_storage_out_item.id = 835;
        -- skuID=11
        update wms_storage_out_item set after_transaction_quantities = 441 WHERE wms_storage_out_item.id = 8413;
        update wms_storage_out_item set after_transaction_quantities = 442 WHERE wms_storage_out_item.id = 8404;
        update wms_storage_out_item set after_transaction_quantities = 443 WHERE wms_storage_out_item.id = 8393;
        -- skuId=16
        update wms_storage_out_item set after_transaction_quantities = 397 WHERE wms_storage_out_item.id = 8415;
        -- skuId=16
        update wms_storage_out_item set after_transaction_quantities = 398 WHERE wms_storage_out_item.id = 8406;
        update wms_storage_out_item set after_transaction_quantities = 399 WHERE wms_storage_out_item.id = 8387;
        update wms_storage_out_item set after_transaction_quantities = 898 WHERE wms_storage_out_item.id = 967;
        update wms_storage_out_item set after_transaction_quantities = 74 WHERE wms_storage_out_item.id = 8395;
        update wms_storage_out_item set after_transaction_quantities = 75 WHERE wms_storage_out_item.id = 8140;
        update wms_storage_out_item set after_transaction_quantities = 85 WHERE wms_storage_out_item.id = 8119;
        update wms_storage_out_item set after_transaction_quantities = 87 WHERE wms_storage_out_item.id = 8081;
        update wms_storage_out_item set after_transaction_quantities = 92 WHERE wms_storage_out_item.id = 8187;
        update wms_storage_out_item set after_transaction_quantities = 94 WHERE wms_storage_out_item.id = 939;
        -- skuId=6
        update wms_storage_out_item set after_transaction_quantities = 451 WHERE wms_storage_out_item.id = 8416;
        -- skuId=2
        update wms_storage_out_item set after_transaction_quantities = 306 WHERE wms_storage_out_item.id = 8405;
        update wms_storage_out_item set after_transaction_quantities = 178 WHERE wms_storage_out_item.id = 938;
        update wms_storage_out_item set after_transaction_quantities = 166 WHERE wms_storage_out_item.id = 8185;
        update wms_storage_out_item set after_transaction_quantities = 164 WHERE wms_storage_out_item.id = 8074;
        update wms_storage_out_item set after_transaction_quantities = 97 WHERE wms_storage_out_item.id = 8112;
        update wms_storage_out_item set after_transaction_quantities = 64 WHERE wms_storage_out_item.id = 8133;
        -- skuId=5
        update wms_storage_out_item set after_transaction_quantities = 306 WHERE wms_storage_out_item.id = 8392;
        -- skuId=11
        update wms_storage_out_item set after_transaction_quantities = 942 WHERE wms_storage_out_item.id = 964;
        update wms_storage_out_item set after_transaction_quantities = 30 WHERE wms_storage_out_item.id = 8139;
        update wms_storage_out_item set after_transaction_quantities = 35 WHERE wms_storage_out_item.id = 8118;
        update wms_storage_out_item set after_transaction_quantities = 47 WHERE wms_storage_out_item.id = 8080;
        update wms_storage_out_item set after_transaction_quantities = 49 WHERE wms_storage_out_item.id = 8186;
        update wms_storage_out_item set after_transaction_quantities = 51 WHERE wms_storage_out_item.id = 936;
        -- skuId=17
        update wms_storage_out_item set after_transaction_quantities = 304 WHERE wms_storage_out_item.id = 8394;
        update wms_storage_out_item set after_transaction_quantities = 305 WHERE wms_storage_out_item.id = 8386;
        update wms_storage_out_item set after_transaction_quantities = 142 WHERE wms_storage_out_item.id = 8408;
        update wms_storage_out_item set after_transaction_quantities = 144 WHERE wms_storage_out_item.id = 8141;
        update wms_storage_out_item set after_transaction_quantities = 152 WHERE wms_storage_out_item.id = 8120;
        update wms_storage_out_item set after_transaction_quantities = 173 WHERE wms_storage_out_item.id = 8082;
        update wms_storage_out_item set after_transaction_quantities = 175 WHERE wms_storage_out_item.id = 8188;
        -- skuId=8
        update wms_storage_out_item set after_transaction_quantities = 84 WHERE wms_storage_out_item.id = 8142;
        update wms_storage_out_item set after_transaction_quantities = 89 WHERE wms_storage_out_item.id = 8121;
        update wms_storage_out_item set after_transaction_quantities = 94 WHERE wms_storage_out_item.id = 8083;
        update wms_storage_out_item set after_transaction_quantities = 96 WHERE wms_storage_out_item.id = 8189;
        -- skuId=9
        update wms_storage_out_item set after_transaction_quantities = 873 WHERE wms_storage_out_item.id = 966;
        update wms_storage_out_item set after_transaction_quantities = 75 WHERE wms_storage_out_item.id = 8143;
        update wms_storage_out_item set after_transaction_quantities = 85 WHERE wms_storage_out_item.id = 8122;
        update wms_storage_out_item set after_transaction_quantities = 101 WHERE wms_storage_out_item.id = 8084;
        update wms_storage_out_item set after_transaction_quantities = 106 WHERE wms_storage_out_item.id = 8190;
        -- skuId=13
        update wms_storage_out_item set after_transaction_quantities = 283 WHERE wms_storage_out_item.id = 8385;
        update wms_storage_out_item set after_transaction_quantities = 160 WHERE wms_storage_out_item.id = 8146;
        update wms_storage_out_item set after_transaction_quantities = 170 WHERE wms_storage_out_item.id = 8125;
        update wms_storage_out_item set after_transaction_quantities = 189 WHERE wms_storage_out_item.id = 8087;
        update wms_storage_out_item set after_transaction_quantities = 194 WHERE wms_storage_out_item.id = 8191;
        -- skuId=14
        update wms_storage_out_item set after_transaction_quantities = 443 WHERE wms_storage_out_item.id = 8414;
        update wms_storage_out_item set after_transaction_quantities = 943 WHERE wms_storage_out_item.id = 965;
        update wms_storage_out_item set after_transaction_quantities = 34 WHERE wms_storage_out_item.id = 8407;
        update wms_storage_out_item set after_transaction_quantities = 35 WHERE wms_storage_out_item.id = 8397;
        update wms_storage_out_item set after_transaction_quantities = 40 WHERE wms_storage_out_item.id = 8147;
        update wms_storage_out_item set after_transaction_quantities = 48 WHERE wms_storage_out_item.id = 8126;
        update wms_storage_out_item set after_transaction_quantities = 52 WHERE wms_storage_out_item.id = 8192;
        update wms_storage_out_item set after_transaction_quantities = 53 WHERE wms_storage_out_item.id = 937;
        update wms_storage_out_item set after_transaction_quantities = 54 WHERE wms_storage_out_item.id = 928;
        -- skuId=18
        update wms_storage_out_item set after_transaction_quantities = 78 WHERE wms_storage_out_item.id = 8149;
        update wms_storage_out_item set after_transaction_quantities = 83 WHERE wms_storage_out_item.id = 8128;
        update wms_storage_out_item set after_transaction_quantities = 90 WHERE wms_storage_out_item.id = 8090;
        update wms_storage_out_item set after_transaction_quantities = 92 WHERE wms_storage_out_item.id = 8193;
        -- skuId=21
        update wms_storage_out_item set after_transaction_quantities = 195 WHERE wms_storage_out_item.id = 962;
        -- skuId=87
        update wms_storage_out_item set after_transaction_quantities = 1364 WHERE wms_storage_out_item.id = 8310;
        update wms_storage_out_item set after_transaction_quantities = 1376 WHERE wms_storage_out_item.id = 8313;
        -- skuId=88
        update wms_storage_out_item set after_transaction_quantities = 1403 WHERE wms_storage_out_item.id = 8314;
        update wms_storage_out_item set after_transaction_quantities = 1385 WHERE wms_storage_out_item.id = 8311;
        -- skuId=89
        update wms_storage_out_item set after_transaction_quantities = 1356 WHERE wms_storage_out_item.id = 8315;
        update wms_storage_out_item set after_transaction_quantities = 1348 WHERE wms_storage_out_item.id = 8312;
        -- skuId=03
        update wms_storage_out_item set after_transaction_quantities = 95 WHERE wms_storage_out_item.id = 961;
        update wms_storage_out_item set after_transaction_quantities = 94 WHERE wms_storage_out_item.id = 963;


        ##入库子项相关:
        -- in_item
        -- skuId =1
        update wms_storage_in_item set after_transaction_quantities = 900 WHERE wms_storage_in_item.id = 906;
        -- skuId =2
        update wms_storage_in_item set after_transaction_quantities = 1000 WHERE wms_storage_in_item.id = 985;
        -- skuId =3
        update wms_storage_in_item set after_transaction_quantities = 1000 WHERE wms_storage_in_item.id = 983;
        -- skuId =21
        update wms_storage_in_item set after_transaction_quantities = 767 WHERE wms_storage_in_item.id = 984;
        -- skuId =62
        update wms_storage_in_item set after_transaction_quantities = 3000 WHERE wms_storage_in_item.id = 981;
        -- skuId =64
        update wms_storage_in_item set after_transaction_quantities = 3000 WHERE wms_storage_in_item.id = 982;
        -- skuId =69
        update wms_storage_in_item set after_transaction_quantities = 2000 WHERE wms_storage_in_item.id = 965;
        -- skuId =70
        update wms_storage_in_item set after_transaction_quantities = 4000 WHERE wms_storage_in_item.id = 966;
        -- skuId =73
        update wms_storage_in_item set after_transaction_quantities = 1885 WHERE wms_storage_in_item.id = 937;
        -- skuId =74
        update wms_storage_in_item set after_transaction_quantities = 2000 WHERE wms_storage_in_item.id = 961;
        -- skuId =75
        update wms_storage_in_item set after_transaction_quantities = 2000 WHERE wms_storage_in_item.id = 962;
        -- skuId =76
        update wms_storage_in_item set after_transaction_quantities = 2000 WHERE wms_storage_in_item.id = 969;
        -- skuId =77
        update wms_storage_in_item set after_transaction_quantities = 1912 WHERE wms_storage_in_item.id = 938;
        -- skuId =78
        update wms_storage_in_item set after_transaction_quantities = 2000 WHERE wms_storage_in_item.id = 963;
        -- skuId =79
        update wms_storage_in_item set after_transaction_quantities = 2000 WHERE wms_storage_in_item.id = 964;
        -- skuId =80
        update wms_storage_in_item set after_transaction_quantities = 2000 WHERE wms_storage_in_item.id = 970;
        -- skuId =81
        update wms_storage_in_item set after_transaction_quantities = 1865 WHERE wms_storage_in_item.id = 939;
        -- skuId =84
        update wms_storage_in_item set after_transaction_quantities = 6350 WHERE wms_storage_in_item.id = 954;
        -- skuId =85
        update wms_storage_in_item set after_transaction_quantities = 6350 WHERE wms_storage_in_item.id = 953;
        -- skuId =86
        update wms_storage_in_item set after_transaction_quantities = 6350 WHERE wms_storage_in_item.id = 952;
        -- skuId =87
        update wms_storage_in_item set after_transaction_quantities = 1885 WHERE wms_storage_in_item.id = 945;
        -- skuId =88
        update wms_storage_in_item set after_transaction_quantities = 1912 WHERE wms_storage_in_item.id = 944;
        -- skuId =89
        update wms_storage_in_item set after_transaction_quantities = 1865 WHERE wms_storage_in_item.id = 943;

    #采购入库的需求数量为采购的数量 24/04/2019
        ##插入 采购的总数
        ###item.setDemandQuantities(skuProcurementCount);

    #采购入库的入库单 需求数量要是 采购的数量 25/04/2019
        UPDATE wms_storage_in_item SET demand_quantities = 2000  WHERE id = 939;
        UPDATE wms_storage_in_item SET demand_quantities = 2000  WHERE id = 938;

        UPDATE wms_storage_in_item SET demand_quantities = 2000  WHERE id = 937;

        UPDATE wms_storage_in_item SET demand_quantities = 2000  WHERE id = 1041;
        UPDATE wms_storage_in_item SET demand_quantities = 1000  WHERE id = 985;
        UPDATE wms_storage_in_item SET demand_quantities = 1000  WHERE id = 984;
        UPDATE wms_storage_in_item SET demand_quantities = 1000  WHERE id = 983;
        UPDATE wms_procurement SET procure_status = 'TotalStorageIn'  WHERE id = 35;
        UPDATE wms_procurement SET procure_status = 'TotalStorageIn'  WHERE id = 33;
        UPDATE wms_procurement SET procure_status = 'TotalStorageIn'  WHERE id = 32;