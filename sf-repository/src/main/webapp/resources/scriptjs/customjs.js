$(document).ready(function () {
	renderizarComponentes();
});

const renderizarComponentes = () => {
	let numeroItens = $('.loop .elements').length;
	if(numeroItens > 6){
		paginator(numeroItens);
	}
}

const paginacao = (numeroItens) => {
	let limitePorPagina = 6;
	$(`.loop .elements:gt(${limitePorPagina - 1})`).hide();
	let totalPaginas = Math.ceil(numeroItens / limitePorPagina);
	$('.pagination-custom').append(`<ul class="pagination"></ul>`);

	if (numeroItens !== 0) {
		$('.pagination').append(`<li id="prev-item" class="page-item"><a class="page-link" href="javascript:void(0)">Anterior</a></li>`)
		for (let i = 1; i <= totalPaginas; i++) {
			if (i == 1) {
				$('.pagination').append(`<li class="page-item current-page active" ><a class="page-link" href="javascript:void(0)">${i}</a></li>`);
			} else {
				$('.pagination').append(`<li class="page-item current-page" ><a class="page-link" href="javascript:void(0)">${i}</a></li>`);
			}
		}
		$('.pagination').append(`<li class="page-item" id="prox-item"><a class="page-link" href="javascript:void(0)">Próximo</a></li>`);
	}

	$('.pagination li.current-page').on('click', function () {
		if ($(this).hasClass('active')) {
			return false;
		} else {
			let paginaAtual = $(this).index();
			$('.pagination li').removeClass('active');
			$(this).addClass('active');
			$(`.loop .elements`).hide();
			let totalElementosPagina = limitePorPagina * paginaAtual;
			for (let i = totalElementosPagina - limitePorPagina; i < totalElementosPagina; i++) {
				$(`.loop .elements:eq(${i})`).show();

			}
		}
	})

	$('#prox-item').on('click', function () {
		let paginaAtual = $('.pagination li.active').index();

		if (paginaAtual === totalPaginas) {
			return false;
		} else {
			paginaAtual++;
			$('.pagination li').removeClass('active');
			$(`.loop .elements`).hide();
			let totalElementosPagina = limitePorPagina * paginaAtual;
			for (let i = totalElementosPagina - limitePorPagina; i < totalElementosPagina; i++) {
				$(`.loop .elements:eq(${i})`).show();

			}
			$(`.pagination li.current-page:eq(${paginaAtual - 1})`).addClass('active');
		}
		console.log(paginaAtual);
	});

	$('#prev-item').on('click', function () {
		let paginaAtual = $('.pagination li.active').index();

		if (paginaAtual === 1) {
			return false;
		} else {
			paginaAtual--;
			$('.pagination li').removeClass('active');
			$(`.loop .elements`).hide();
			let totalElementosPagina = limitePorPagina * paginaAtual;
			for (let i = totalElementosPagina - limitePorPagina; i < totalElementosPagina; i++) {
				$(`.loop .elements:eq(${i})`).show();

			}
			$(`.pagination li.current-page:eq(${paginaAtual - 1})`).addClass('active');
		}
		console.log(paginaAtual);
	});
}
const paginator = (numeroItens) => {
	let limitePorPagina = 8;
	let paginaAtual = 1;
	$(`.loop .elements:gt(${limitePorPagina - 1})`).hide();
	let totalPaginas = Math.ceil(numeroItens / limitePorPagina);
	$('.pagination-custom').append(`<ul class="pagination justify-content-center"></ul>`);
	$('.pagination').append(`<li id="prev-item" class="page-item"><a class="page-link ui-state-disabled" href="javascript:void(0)">Anterior</a></li>`);
	$('.pagination').append(`<li id="prox-item" class="page-item"><a class="page-link ui-state-disabled" href="javascript:void(0)">Próximo</a></li>`);

	if (paginaAtual === 1 && totalPaginas === 1) {
		$('#prev-item').addClass('ui-state-disabled');
		$('#prox-item').addClass('ui-state-disabled');
	} else if (paginaAtual === 1) {
		$('#prev-item').addClass('ui-state-disabled');
	}
 

	$('#prox-item').on('click', () => {
		paginaAtual++;
		if (paginaAtual === totalPaginas && paginaAtual !== 1) {
			$('#prox-item').addClass('ui-state-disabled');
			$('#prev-item').removeClass('ui-state-disabled');
		} else if (paginaAtual === 2) {
			$('#prev-item').removeClass('ui-state-disabled');

		}
		$(`.loop .elements`).hide();

		let totalElementosPagina = limitePorPagina * paginaAtual;
		for (let i = totalElementosPagina - limitePorPagina; i < totalElementosPagina; i++) {
			$(`.loop .elements:eq(${i})`).show();
		}

		console.log(paginaAtual);
	})

	$('#prev-item').on('click', () => {
		paginaAtual--;
		if (paginaAtual === 1 && totalPaginas !== 1) {
			$('#prev-item').addClass('ui-state-disabled');
			$('#prox-item').removeClass('ui-state-disabled');
		} else if (paginaAtual < totalPaginas) {
			$('#prox-item').removeClass('ui-state-disabled');
		}
		$(`.loop .elements`).hide();
		let totalElementosPagina = limitePorPagina * paginaAtual;
		for (let i = totalElementosPagina - limitePorPagina; i < totalElementosPagina; i++) {
			$(`.loop .elements:eq(${i})`).show();
		}

		console.log(paginaAtual);
	})



}