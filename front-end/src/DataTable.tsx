import { useState, useEffect } from "react";
import { useMemo } from "react";
import { useTable, useSortBy } from "react-table";

const DataTable = () => {
  const [data, setData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/igracke");
        const result = await response.json();
        setData(result);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, []);

  const columns = useMemo(
    () => [
      { Header: "Ime ljubimca", accessor: "ljubimac.ime" },
      { Header: "Vrsta", accessor: "ljubimac.vrsta" },
      { Header: "Spol", accessor: "ljubimac.spol" },
      { Header: "Boja Ljubimca", accessor: "ljubimac.boja" },
      { Header: "Starost", accessor: "ljubimac.starost" },
      { Header: "Ime vlasnika", accessor: "ljubimac.imeVlasnika" },
      { Header: "Prehrana", accessor: "ljubimac.prehrana" },
      { Header: "Životni vijek", accessor: "ljubimac.zivotniVijek" },
      { Header: "Naziv Igracke", accessor: "naziv" },
      { Header: "Boja Igracke", accessor: "boja" },
    ],
    []
  );

  const { getTableProps, getTableBodyProps, headerGroups, rows, prepareRow } =
    useTable(
      {
        columns,
        data,
      },
      useSortBy
    );

  return (
    <div>
      <table {...getTableProps()} border="1">
        <thead>
          {headerGroups.map((headerGroup) => (
            <tr {...headerGroup.getHeaderGroupProps()}>
              {headerGroup.headers.map((column) => (
                <th {...column.getHeaderProps(column.getSortByToggleProps())}>
                  {column.render("Header")}
                  {column.isSorted ? (column.isSortedDesc ? " ↓" : " ↑") : ""}
                </th>
              ))}
            </tr>
          ))}
        </thead>
        <tbody {...getTableBodyProps()}>
          {rows.map((row) => {
            prepareRow(row);
            return (
              <tr {...row.getRowProps()}>
                {row.cells.map((cell) => (
                  <td {...cell.getCellProps()}>{cell.render("Cell")}</td>
                ))}
              </tr>
            );
          })}
        </tbody>
      </table>
    </div>
  );
};

export default DataTable;
