package com.snb.customeraccount.dto;

import java.util.List;


/**
 * The customer DTO
 */
    public class CustomerDTO {
        private Long id;
        private String name;
        private String surname;
        private List<AccountDTO> accounts;

        // Private constructor to prevent direct instantiation
        private CustomerDTO() {}

        public static class Builder {
            private Long id;
            private String name;
            private String surname;
            private List<AccountDTO> accounts;

            public Builder() {}

            public Builder withId(Long id) {
                this.id = id;
                return this;
            }

            public Builder withName(String name) {
                this.name = name;
                return this;
            }

            public Builder withSurname(String surname) {
                this.surname = surname;
                return this;
            }

            public Builder withAccounts(List<AccountDTO> accounts) {
                this.accounts = accounts;
                return this;
            }

            public CustomerDTO build() {
                CustomerDTO customerDTO = new CustomerDTO();
                customerDTO.id = this.id;
                customerDTO.name = this.name;
                customerDTO.surname = this.surname;
                customerDTO.accounts = this.accounts;
                return customerDTO;
            }
        }

		/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * @return the name
		 */
		public String getName() {
			return name;
		}

		/**
		 * @param name the name to set
		 */
		public void setName(String name) {
			this.name = name;
		}

		/**
		 * @return the surname
		 */
		public String getSurname() {
			return surname;
		}

		/**
		 * @param surname the surname to set
		 */
		public void setSurname(String surname) {
			this.surname = surname;
		}

		/**
		 * @return the accounts
		 */
		public List<AccountDTO> getAccounts() {
			return accounts;
		}

		/**
		 * @param accounts the accounts to set
		 */
		public void setAccounts(List<AccountDTO> accounts) {
			this.accounts = accounts;
		}

       
    }
