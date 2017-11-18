alter table customer drop foreign key FKcgns38kj0663y4u8xygp2hh5x
alter table customer drop foreign key FK_r8whbd0mf9er6vwfr0sclsxkd
alter table employee drop foreign key FK_qrbsk9ljmhfje93me0n7xwdxq
alter table employee_tours drop foreign key FKn8fns7yfhunqtl4d1bv95ojcw
alter table employee_tours drop foreign key FKl5pswor3u4ne1xrnhs6meeq32
alter table facility drop foreign key FKbpfxetgowgub5rnee0iy0nd4c
alter table facility_employees drop foreign key FK9rsia4u7chygo3aqdyurmvbpa
alter table facility_employees drop foreign key FKjff87l2730eo38w13ofh36u5v
alter table facility_vehicles drop foreign key FKlt999d6an43ryhlyelgyagb2w
alter table facility_vehicles drop foreign key FK5k6cuaj3lmxvlcwiako30w9rd
alter table shipment drop foreign key FK4eyso4aveske6x7kanwgs0st2
alter table shipment drop foreign key FK116vumo70hiohe3ba5dtxgcep
alter table shipment drop foreign key FKeunsl3l76wuix4w2hspt45rge
alter table shipment_shipment_status drop foreign key FKlfkxl3yxft5ks6a2b1qjh73uv
alter table shipment_shipment_status drop foreign key FK6id294l8g5oqn85nfmt6xxgdo
alter table shipment_status drop foreign key FK9n5cmjvngpjyn77dje20xn9b
alter table shipment_tours drop foreign key FK39el6wr7idvhhstyt4yngs2oc
alter table shipment_tours drop foreign key FK1j3xbia2osl0gls3gs99vg8ga
alter table tour drop foreign key FK87uucj7tddcnm3hyfndgojyui
alter table tour drop foreign key FKmbhmg2a51s4xuacbsd8le5k8x
alter table tour drop foreign key FKjgdo00nh8di1lyi9qid2b9gft
alter table tour drop foreign key FKcwewqtp5rn9qo1jkkj7g1cdd2
alter table tour_shipments drop foreign key FKh18shrh04cxlkurcs0wo5uram
alter table tour_shipments drop foreign key FKlif84osrniyi4bwpu9byytjus
alter table vehicle_tours drop foreign key FKnrv4u9sjmbl0on54jyapydor7
alter table vehicle_tours drop foreign key FKsdg3nwthmiucwxt2ja1ar4g2a
drop table if exists address
drop table if exists customer
drop table if exists employee
drop table if exists employee_tours
drop table if exists facility
drop table if exists facility_employees
drop table if exists facility_vehicles
drop table if exists payment
drop table if exists shipment
drop table if exists shipment_shipment_status
drop table if exists shipment_status
drop table if exists shipment_tours
drop table if exists tour
drop table if exists tour_shipments
drop table if exists vehicle
drop table if exists vehicle_tours
