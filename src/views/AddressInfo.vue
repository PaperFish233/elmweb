<template>
  <div class="wrapper">
    <!-- header部分 -->
    <header>
      <p>地址管理</p>
    </header>
    <!-- 地址列表部分 -->
    <ul class="addresslist">
      <li v-for="item in deliveryAddressArr" :key="item.daId">
        <div class="addresslist-left" @click="setDeliveryAddress(item)">
          <h3>
            {{ item.contactName }}{{ item.contactSex == 1 ? "先生" : "女士" }}
            {{ item.contactTel }}
          </h3>
          <p>{{ item.address }}</p>
        </div>
        <div class="addresslist-right">
          <i class="fa fa-edit" @click="editUserAddress(item.daId)"></i>
          <i class="fa fa-remove" @click="removeUserAddress(item.daId)"></i>
        </div>
      </li>
    </ul>

    <!-- 新增地址部分 -->
    <div class="addbtn" @click="toAddUserAddress">
      <i class="fa fa-plus-circle"></i>
      <p>新增收货地址</p>
    </div>

    <!-- 底部菜单部分 -->
    <Footer></Footer>
  </div>
</template>

<script setup>
import Footer from "../components/Footer.vue";
import qs from "qs";
import { useRouter, useRoute } from "vue-router";
import { ref, inject } from "vue";
import {
  getSessionStorage,
  getLocalStorage,
  setLocalStorage,
  removeLocalStorage,
} from "../common.js";

const axios = inject("axios");
const router = useRouter();
const route = useRoute();

const user = getSessionStorage("user");
const businessId = route.query.businessId;
const deliveryAddressArr = ref([]);

const listDeliveryAddressByUserId = () => {
  //查询送货地址
  axios
    .post(
      "DeliveryAddressController/listDeliveryAddressByUserId",
      qs.stringify({ userId: user.userId })
    )
    .then((response) => {
      deliveryAddressArr.value = response.data;
    })
    .catch((error) => {
      console.error(error);
    });
};
listDeliveryAddressByUserId();

const setDeliveryAddress = (deliveryAddress) => {
  //把用户选择的默认送货地址存储到localStorage中
  setLocalStorage(user.userId, deliveryAddress);
  router.push({
    path: "/order",
    query: { businessId: businessId },
  });
};

const toAddUserAddress = () => {
  router.push({
    path: "/adduseraddress",
    query: { businessId: businessId },
  });
};

const editUserAddress = (daId) => {
  router.push({
    path: "/edituseraddress",
    query: { businessId: businessId, daId: daId },
  });
};

const removeUserAddress = (daId) => {
  if (!confirm("确认要删除此送货地址吗？")) {
    return;
  }

  axios
    .post("DeliveryAddressController/removeDeliveryAddress", qs.stringify({ daId: daId }))
    .then((response) => {
      if (response.data > 0) {
        let deliveryAddress = getLocalStorage(user.userId);
        if (deliveryAddress != null && deliveryAddress.daId == daId) {
          removeLocalStorage(user.userId);
        }
        listDeliveryAddressByUserId();
      } else {
        alert("删除地址失败！");
      }
    })
    .catch((error) => {
      console.error(error);
    });
};
</script>

<style scoped>
/*************** 总容器 ***************/
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #f5f5f5;
}

/*************** header ***************/
.wrapper header {
  width: 100%;
  height: 12vw;
  background-color: #0097ff;
  display: flex;
  justify-content: space-around;
  align-items: center;
  color: #fff;
  font-size: 4.8vw;
  position: fixed;
  left: 0;
  top: 0;
  /*保证在最上层*/
  z-index: 1000;
}

/*************** addresslist ***************/
.wrapper .addresslist {
  width: 100%;
  margin-top: 12vw;
  background-color: #fff;
}

.wrapper .addresslist li {
  width: 100%;
  box-sizing: border-box;
  border-bottom: solid 1px #ddd;
  padding: 3vw;

  display: flex;
}

.wrapper .addresslist li .addresslist-left {
  flex: 5;
  /*左边这块区域是可以点击的*/
  user-select: none;
  cursor: pointer;
}

.wrapper .addresslist li .addresslist-left h3 {
  font-size: 4.6vw;
  font-weight: 300;
  color: #666;
}

.wrapper .addresslist li .addresslist-left p {
  font-size: 4vw;
  color: #666;
}

.wrapper .addresslist li .addresslist-right {
  flex: 1;
  font-size: 5.6vw;
  color: #999;
  cursor: pointer;

  display: flex;
  justify-content: space-around;
  align-items: center;
}

/*************** 新增地址部分 ***************/
.wrapper .addbtn {
  width: 100%;
  height: 14vw;
  border-top: solid 1px #ddd;
  border-bottom: solid 1px #ddd;
  background-color: #fff;
  margin-top: 4vw;

  display: flex;
  justify-content: center;
  align-items: center;

  font-size: 4.5vw;
  color: #0097ff;
  user-select: none;
  cursor: pointer;
}

.wrapper .addbtn p {
  margin-left: 2vw;
}
</style>
