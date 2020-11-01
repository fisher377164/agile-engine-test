<template>
  <div>
    <h1>Payment Transactions for user 1</h1>
    <b-alert v-model="showErrorsAlert" variant="danger" dismissible>
      {{ errors }}
    </b-alert>

    <div class="accordion" role="tablist">
      <b-card no-body class="mb-1" v-for="(transaction, index) in paymentTransactions.page" :key="transaction.id">
        <b-card-header header-tag="header" class="p-1" role="tab">
          <b-button block v-b-toggle="'accordion-'+index" :variant="transaction.type === 'DEBIT'? 'info' : 'danger'">
            {{ transaction.type }} {{ transaction.amount }}
          </b-button>
        </b-card-header>
        <b-collapse :id="'accordion-'+index" accordion="my-accordion" role="tabpanel">
          <b-card-body>
            <b-card-text>Id <code>{{ transaction.id }}</code></b-card-text>
            <b-card-text>TransactionId <code>{{ transaction.transactionId }}</code></b-card-text>
            <b-card-text>AccountId <code>{{ transaction.accountId }}</code></b-card-text>
            <b-card-text>Type <code>{{ transaction.type }}</code></b-card-text>
            <b-card-text>Amount <code>{{ transaction.amount }}</code></b-card-text>
            <b-card-text>Created at <code>{{ transaction.timestamp }}</code></b-card-text>
          </b-card-body>
        </b-collapse>
      </b-card>
    </div>

    <b-button v-on:click="getPreviousPaymentTransactions" variant="light">Previous</b-button>
    <b-button v-on:click="getNextPaymentTransactions" variant="light">Next</b-button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      paymentTransactions: {
        page: [],
        totalCount: 0
      },
      errors: {},
      showErrorsAlert: false,

      page: 0
    }
  },

  methods: {
    getPreviousPaymentTransactions: function () {
      this.page--;
      this.getPaymentTransactions();
    },
    getNextPaymentTransactions: function () {
      this.page++;
      this.getPaymentTransactions();
    },
    getPaymentTransactions: function () {
      axios.get(`http://localhost:8081/api/payment_transaction?pageIndex=${this.page}&pageSize=10`)
          .then(response => this.paymentTransactions = response.data)
          .catch(error => {
            if (error.response) {
              this.errors = error.response.data;
            } else {
              this.errors = error;
            }
            this.showErrorsAlert = true;
          });
    }
  },

  created() {
    this.getPaymentTransactions()
  },
}
</script>
