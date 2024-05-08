-- Inserir as vagas no banco de dados
insert into vaga (alterado_por, criado_por, data_alteracao, data_criacao, status_vaga, status, id_veiculo)
select 'admin' as alterado_por, 'admin' as criado_por, current_timestamp as data_alteracao, current_timestamp as data_criacao, 'L' as status_vaga, 'A' as status, null as id_veiculo
from generate_series(1, 200);
