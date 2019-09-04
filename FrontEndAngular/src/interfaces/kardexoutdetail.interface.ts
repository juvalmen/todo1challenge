export interface Kardexoutdetail{
  idkardexout:number;
	quantity: number;
	idproduct: number;
	productname: string;
	stockmovement: number;
	creationdate: Date;
	uid?: number;
}