<script></script>

<template>
  <div
    class="modal fade"
    id="exampleModal"
    tabindex="1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog modal-lg modal-dialog-scrollable">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModal">{{ userName }}님의 여행 😎</h5>
          <button type="button" class="close" data-bs-dismiss="modal" aria-label="Close">
            <span aria-hidden="true"><i class="fa fa-close"></i></span>
          </button>
        </div>
        <div class="modal-body">
          <!-- <div class="book">
            <img
              src="https://i.pinimg.com/564x/46/ac/60/46ac6067341ded58d7ec67510189e125.jpg"
              alt="the cast of Schitt’s Creek"
            />
          </div> -->
          <div class="planDetailTitle">
            <h1>{{ planD['plan'].title }}</h1>
            <div class="detailBtns">
              <button
                class="btn-get-started"
                data-bs-dismiss="modal"
                aria-label="Close"
                @click="articleBtn"
              >
                {{ articleOrnot }}
              </button>
              <!-- <button class="deleteBtn">수정</button> -->
              <button class="deleteBtn" @click="deletePlan">삭제</button>
            </div>
            <h5>출발일 : {{ planD['plan'].startDate }}</h5>
            <h5>도착일 : {{ planD['plan'].endDate }}</h5>
          </div>
          <!-- /*timeline*/ -->
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <div class="cardPlan">
                  <div class="card-body">
                    <h6 class="card-title">Timeline</h6>
                    <div id="content" class="mb-4">
                      <ul class="timeline">
                        <li
                          class="event"
                          :data-date="p"
                          v-for="(p, key, index) in planD.planDays"
                          :key="index"
                        >
                          <div v-for="(attr, key, index) in planD['plans'][p]" :key="index">
                            <h3 class="title">{{ attr.title }}</h3>
                            <p>
                              {{ attr.addr1 + ' ' + attr.addr2 }}
                            </p>
                          </div>
                        </li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.detailBtns {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-end;
  align-items: center;
  position: relative;
  height: 0px;
  top: -75px;
}
.deleteBtn {
  border: none;
  padding: 7px 10px;
  border-radius: 8px;
  background-color: var(--color-red);
  color: var(--color-white);
}
.planDetailTitle {
  display: flex;
  width: 80%;
  flex-direction: column;
  margin: auto;
}
.planDetailTitle h5 {
  font-size: 1rem;
  text-align: left;
  font-weight: bold;
}

.modal-body {
  margin: auto;
  width: 100%;
}

/*book image*/
.book {
  width: 18.5em;
  height: 23.0991em;
  margin-top: -4.4em;
  position: relative;
  left: 30%;
  transform: perspective(60em) rotateX(58deg) rotateZ(-34deg) skewY(-7deg);
  box-shadow:
    -1.4em 1.7em 0.3em -0.3em rgba(0, 0, 0, 0.8),
    -1.6em 1.8em 0.9em -0.2em rgba(0, 0, 0, 0.5),
    0.3em 1.9em 1.3em rgba(0, 0, 0, 0.3);
  border-top-right-radius: 0.4em;
}
.book img {
  border-top-right-radius: 0.4em;
  box-sizing: border-box;
  width: 100%;
  clip: rect(0em, 18.5em, 23.1em, 0em);
  display: block;
  position: absolute;
  filter: saturate(90%);
}
.book:before,
.book:after {
  content: '';
  position: absolute;
  top: 0;
}
.book:before {
  width: 105%;
  height: 105%;
  left: -5%;
  z-index: -1;
  background-repeat: no-repeat;
  background-image: linear-gradient(
      115deg,
      transparent 2.8%,
      #3f3f3f 3%,
      #3f3f3f 16%,
      transparent 16%
    ),
    linear-gradient(125deg, transparent 10%, #3f3f3f 10%, #3f3f3f 17%, #222 46.8%, transparent 47%),
    linear-gradient(
      125deg,
      transparent 46%,
      rgba(0, 0, 0, 0.5) 46.5%,
      rgba(0, 0, 0, 0.25) 49%,
      transparent 53%
    ),
    linear-gradient(to right, #444, #666), linear-gradient(#444, #444),
    linear-gradient(140deg, transparent 45%, #eee 45%, #ccc 96.8%, rgba(170, 170, 170, 0) 97%);
  background-size:
    100% 100%,
    100% 100%,
    100% 100%,
    100% 0.4em,
    94% 0.2em,
    100% 100%;
  background-position:
    0 0,
    0 0,
    0 0,
    0 95.8%,
    0 100%,
    0 0;
}
.book:after {
  width: 100%;
  height: 100%;
  background-repeat: no-repeat;
  background-image: linear-gradient(
      to right,
      transparent 2%,
      rgba(0, 0, 0, 0.1) 3%,
      rgba(0, 0, 0, 0.1) 4%,
      transparent 5%
    ),
    linear-gradient(-50deg, rgba(0, 0, 0, 0.1) 20%, transparent 100%),
    linear-gradient(-50deg, rgba(0, 0, 0, 0.2) 20%, transparent 100%),
    linear-gradient(to bottom, rgba(0, 0, 0, 0.1) 20%, transparent 100%),
    linear-gradient(to bottom, rgba(0, 0, 0, 0.1) 20%, transparent 100%);
  background-size:
    100% 100%,
    2% 20%,
    1% 20%,
    2% 20%,
    1% 20%;
  background-position:
    0 0,
    2.2% 100%,
    3% 100%,
    2.2% 0,
    3% 0;
}

/*timeline*/

.card-title {
  margin-bottom: 30px;
  font-size: 1.55rem;
  width: 80%;
  margin: 30px 7%;
  text-align: left;
}
.timeline {
  border-left: 3px solid var(--color-blue);
  border-bottom-right-radius: 4px;
  border-top-right-radius: 4px;
  background: var(--color-background);
  margin: 0 0 0 25%;
  letter-spacing: 0.2px;
  position: relative;
  line-height: 1.4em;
  font-size: 1.03em;
  padding: 50px;
  list-style: none;
  text-align: left;
  max-width: 70%;
}

@media (max-width: 767px) {
  .timeline {
    max-width: 98%;
    padding: 25px;
  }
}

.timeline h1 {
  font-weight: 300;
  font-size: 1.4em;
}

.timeline h2,
.timeline h3 {
  font-weight: 600;
  font-size: 1rem;
  margin-bottom: 10px;
}

.timeline .event {
  border-bottom: 1px dashed var(--color-semigray);
  padding-bottom: 25px;
  margin-bottom: 25px;
  position: relative;
}

@media (max-width: 767px) {
  .timeline .event {
    padding-top: 30px;
  }
}

.timeline .event:last-of-type {
  padding-bottom: 0;
  margin-bottom: 0;
  border: none;
}

.timeline .event:before,
.timeline .event:after {
  position: absolute;
  display: block;
  top: 0;
}

.timeline .event:before {
  left: -207px;
  content: attr(data-date);
  text-align: right;
  font-weight: 800;
  font-size: 0.9em;
  min-width: 120px;
}

@media (max-width: 767px) {
  .timeline .event:before {
    left: 0px;
    text-align: left;
  }
}

.timeline .event:after {
  -webkit-box-shadow: 0 0 0 3px var(--color-blue);
  box-shadow: 0 0 0 3px var(--color-blue);
  left: -55.8px;
  background: #fff;
  border-radius: 50%;
  height: 9px;
  width: 9px;
  content: '';
  top: 5px;
}

@media (max-width: 767px) {
  .timeline .event:after {
    left: -31.8px;
  }
}

.rtl .timeline {
  border-left: 0;
  text-align: right;
  border-bottom-right-radius: 0;
  border-top-right-radius: 0;
  border-bottom-left-radius: 4px;
  border-top-left-radius: 4px;
  border-right: 3px solid var(--color-blue);
}

.rtl .timeline .event::before {
  left: 0;
  right: -170px;
}

.rtl .timeline .event::after {
  left: 0;
  right: -55.8px;
}
</style>
