export interface Producto{
  idproduct:number;
	description?:string;
	name:string;
	price?: number;
	stock?: number;
	creationdate?: Date;
	modificationdate?: Date;
	idProductCategory?: number;
	valueProductCategory?: string;
	descriptionProductCategory?: string;
}
