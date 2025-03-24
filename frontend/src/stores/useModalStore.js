import { defineStore } from "pinia";

export const useModalStore = defineStore("modal", {
  state: () => ({ isModal: false }),

  actions: {
    openModal() {
      this.isModal = true;
    },
    closeModal() {
      this.isModal = false;
    },
  },
});
